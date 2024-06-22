package com.ejemplo.apirest.ejemplo_apirest.utilities;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class Utilities {
    public static String saveField(MultipartFile multiPart, String route){
        if (Utilities.validateImage(multiPart.getContentType())==false){
            return "NO";
        } else {
            long time = System.currentTimeMillis();
            String name = time+Utilities.getExtension(multiPart.getContentType());
            try {
                File imageFile = new File(route + name);
                multiPart.transferTo(imageFile);
                return name;
            } catch (IOException e) {
                // TODO: handle exception
                return null;
            }
        }
    }

    //Upload image
    //Create extension
    public static boolean validateImage(String mime){
        boolean comeback = false;
        switch(mime){
            case "image/jpeg":
            comeback = true;
            break;
            case "image/jpg":
            comeback = true;
            break;
            case "image/png":
            comeback = true;
            break;
            default:
                comeback = false;
                break;
        }
        return comeback;
    }

    //Get Extension
    public static String getExtension(String mime){
        String comeback = "";
        switch(mime){
            case "image/jpeg":
            comeback = ".jpeg";
            break;
            case "image/jpg":
            comeback = ".jpg";
            break;
            case "image/png":
            comeback = ".png";
            break;
        }
        return comeback;
    }

    //SLUGS
    private static final Pattern MONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    public static String getSlug(String input){
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = MONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, String message){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("fecha", new Date());
            map.put("estado", status.value());
            map.put("mensaje", message);

            return new ResponseEntity<Object>(map, status);
        } catch (Exception e) {
            // TODO: handle exception
            map.put("fecha", new Date());
            map.put("estado", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("mensaje", e.getMessage());

            return new ResponseEntity<Object>(map, status);
        }
    }

}
