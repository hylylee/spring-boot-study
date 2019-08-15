package com.example.demo.cache;

import com.example.demo.dto.TagDTO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js", "php", "html", "java", "node", "python", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "express", "django", "flask", "yii", "ruby-on-rails", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        TagDTO server = new TagDTO();
        server.setCategoryName("服务开发");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "h2", "sql server", "oracle", "mongo", "redis", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "vim", "sublime-text", "maven", "ide", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        tagDTOS.add(program);
        tagDTOS.add(framework);
        tagDTOS.add(server);
        tagDTOS.add(db);
        tagDTOS.add(tool);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        if (split == null) {
            split = new String[]{tags};
        }
        List<TagDTO> tagDTOList = get();
        List<String> tagList = tagDTOList.stream()
                .flatMap(tag -> tag.getTags().stream())
                .collect(Collectors.toList());
        String invalid = Arrays.stream(split)
                .filter(t -> !tagList.contains(t))
                .collect(Collectors.joining(","));
        return invalid;
    }
}
