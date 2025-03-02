package com.example.hr_worker.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class LogToPdfConverterService {
    public static void main() {
        String logFilePath = "logs/sgo-salvemaria.log"; // Caminho do arquivo de log
        String pdfFilePath = "logs/sgo-salvemaria.pdf"; // Caminho do arquivo PDF gerado

        try {
            convertLogToPdf(logFilePath, pdfFilePath);
            System.out.println("PDF gerado com sucesso em: " + pdfFilePath);
        } catch (IOException e) {
            System.err.println("Erro ao converter logs para PDF: " + e.getMessage());
        }
    }

    public static void convertLogToPdf(String logFilePath, String pdfFilePath) throws IOException {
        // Criar um escritor de PDF
        PdfWriter writer = new PdfWriter(pdfFilePath);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Ler os logs do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                document.add(new Paragraph(line)); // Adiciona cada linha como um parágrafo no PDF
            }
        }

        document.close();
        System.out.println("Conversão finalizada! Arquivo salvo em: " + pdfFilePath);
    }
}
