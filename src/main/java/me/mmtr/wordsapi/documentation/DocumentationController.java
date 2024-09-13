package me.mmtr.wordsapi.documentation;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class DocumentationController {

    @GetMapping("/")
    public ResponseEntity<?> index() throws IOException {
        String markdownFileContent = Files.readString(Path.of("README.md"));

        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        String htmlContent = renderer.render(parser.parse(markdownFileContent));
        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }
}
