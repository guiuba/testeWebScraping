import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebScrapingZip {
    private static final String URL_SITE = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

    public static void main(String[] args) {
        try {
            // Baixa HTML
            Document doc = Jsoup.connect(URL_SITE).get();

            // Encontra  links
            List<File> arquivosPDF = new ArrayList<>();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String texto = link.text().trim();
                String urlPdf = link.absUrl("href");

                // Baixa apenas se a URL Anexo I, Anexo II e contem .pdf
                if ((texto.contains("Anexo I") || texto.contains("Anexo II"))
                        && urlPdf.toLowerCase().contains(".pdf")) {
                    File pdf = baixarArquivo(urlPdf);
                    if (pdf != null) {
                        arquivosPDF.add(pdf);
                    }
                }
            }

            // Verifica se encontrou PDFs antes de compactar
            if (!arquivosPDF.isEmpty()) {
                compactarEmZip(arquivosPDF, "anexos.zip");
                System.out.println("Processo concluído! Arquivo ZIP gerado: anexos.zip");
            } else {
                System.out.println("Nenhum arquivo PDF encontrado para compactação.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para baixar os PDFs
    private static File baixarArquivo(String fileUrl) {
        try {
            // Extrai o nome do arquivo da URL
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

            try (InputStream in = new URL(fileUrl).openStream();
                 FileOutputStream out = new FileOutputStream(fileName)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                System.out.println("PDF baixado: " + fileName);
                return new File(fileName);
            }
        } catch (IOException e) {
            System.out.println("Erro ao baixar: " + fileUrl);
            return null;
        }
    }

    // Método para compactar os PDFs em um único ZIP
    private static void compactarEmZip(List<File> arquivos, String zipFileName) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (File file : arquivos) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    zipOut.putNextEntry(new ZipEntry(file.getName()));

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        zipOut.write(buffer, 0, bytesRead);
                    }
                    zipOut.closeEntry();
                }
            }
            System.out.println("ZIP criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao criar ZIP: " + e.getMessage());
        }
    }
}




