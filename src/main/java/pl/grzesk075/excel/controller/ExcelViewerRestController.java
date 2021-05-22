package pl.grzesk075.excel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.grzesk075.excel.model.ExcelViewer;

@Controller
public class ExcelViewerRestController {

    @Autowired
    private ExcelViewer excelViewerModel;

    @GetMapping("/viewer")
    public String excelViewer(Model model) {
        model.addAttribute("title", excelViewerModel.getSheet().getTitle());
        return "/viewer";
    }
}
