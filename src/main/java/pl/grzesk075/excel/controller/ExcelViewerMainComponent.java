package pl.grzesk075.excel.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.grzesk075.excel.dao.Sheet;
import pl.grzesk075.excel.model.ExcelViewer;

@Component
public class ExcelViewerMainComponent {

    public ExcelViewerMainComponent() {
        System.out.println("ExcelViewerMainComponent created.");
    }

    @Bean
    @Scope("singleton")
    public Sheet mainSheet() {
        return new Sheet("Main Sheet Title");
    }

    @Bean
    public ExcelViewer excelViewerModel(Sheet mainSheet) {
        return new ExcelViewer(mainSheet);
    }
}
