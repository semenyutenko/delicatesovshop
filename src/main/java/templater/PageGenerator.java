package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private final String TEMPLATES_DIR;

    private static PageGenerator generator;
    private final Configuration cfg;

    public static PageGenerator instance(String templatesDir){
        if (generator == null){
            generator = new PageGenerator(templatesDir);
        }
        return generator;
    }

    public String getPage(String fileName, Map<String, Object> data){
        Writer stream = new StringWriter();
        try{
            System.out.println(File.separator);
            System.out.println(TEMPLATES_DIR + File.separator + fileName);
            Template template = cfg.getTemplate(fileName);
            template.process(data, stream);
        }catch (IOException | TemplateException e){
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator(String templatesDir){
        TEMPLATES_DIR = templatesDir;
        this.cfg = new Configuration(Configuration.VERSION_2_3_29);
        try {
            cfg.setDirectoryForTemplateLoading(new File(templatesDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
