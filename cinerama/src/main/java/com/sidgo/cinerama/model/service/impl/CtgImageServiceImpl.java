package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.model.dto.CtgImageDTO;
import com.sidgo.cinerama.model.entity.CtgImage;
import com.sidgo.cinerama.model.entity.CtgImageType;
import com.sidgo.cinerama.model.entity.CtgMovie;
import com.sidgo.cinerama.model.repository.CtgImageRepository;
import com.sidgo.cinerama.model.service.CtgImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CtgImageServiceImpl implements CtgImageService {

    @Autowired
    CtgImageRepository ctgImageRepository;

    @Value("${image.path}")
    private String IMAGE_PATH;

    @Value("${application.host}")
    private String APP_HOST;

    @Override
    public List<CtgImageDTO> saveBulk(long idMovie, List<CtgImageDTO> list) {
        List<CtgImageDTO> images = new ArrayList<>();

        try {
            list.forEach(item -> {
                CtgImage image = new CtgImage();
                try {
                    String code = UUID.randomUUID().toString();
                    image.setCode(code);
                    image.setPath(saveFile(idMovie, item.getContent(), code));
                    image.setImageType(new CtgImageType(item.getImageTypeId()));
                    image.setMovie(new CtgMovie(idMovie));

                    image = ctgImageRepository.save(image);

                    images.add(new CtgImageDTO(image));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ex) {
            throw ex;
        }

        return  images;
    }

    @Override
    public CtgImageDTO save(long idMovie, CtgImageDTO imageDTO) {
        CtgImage image = new CtgImage();
        CtgImageDTO finalImage = new CtgImageDTO();
        try {
            String code = UUID.randomUUID().toString();
            image.setCode(code);
            image.setPath(saveFile(idMovie, imageDTO.getContent(), code));
            image.setImageType(new CtgImageType(imageDTO.getImageTypeId()));
            image.setMovie(new CtgMovie(idMovie));

            image = ctgImageRepository.save(image);

            finalImage = new CtgImageDTO(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalImage;
    }

    @Override
    public CtgImageDTO updateImage(long idMovie, long idImage, CtgImageDTO imageDTO) {
        CtgImage image = new CtgImage();

        try {
            image = ctgImageRepository.getOne(idImage);

            image.setPath(saveFile(idMovie, imageDTO.getContent(), image.getCode()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CtgImageDTO(ctgImageRepository.save(image));
    }

    @Override
    public byte[] retrieveImage(String imagePath) {

        try {
            String[] imageDirectory = imagePath.split("\\.");

            File file = new File(IMAGE_PATH + imageDirectory[0] + "/" + imagePath);
            InputStream stream = new FileInputStream(file);

            return StreamUtils.copyToByteArray(stream);
        }
        catch (IOException ex) {
            return new byte[0];
        }

    }

    public String saveFile(long idMovie, String content, String code) throws IOException {
        String path = "";

        String imageDataBytes = content.substring(content.indexOf(",") + 1);

        BufferedImage image;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(imageDataBytes);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        String moviePrefix = "m_" + idMovie;
        String fileName = moviePrefix + "." + code + ".jpeg";
        String fileSufix = moviePrefix + "/" + fileName;

        File outputfile = new File(IMAGE_PATH + fileSufix);
        outputfile.mkdirs();
        outputfile.createNewFile();
        ImageIO.write(image, "jpeg", outputfile);

        return APP_HOST + "/api/v1/images/" + fileName;
    }

}
