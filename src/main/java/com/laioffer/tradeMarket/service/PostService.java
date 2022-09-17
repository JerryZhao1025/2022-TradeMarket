package com.laioffer.tradeMarket.service;

import com.laioffer.tradeMarket.dao.PostDao;
import com.laioffer.tradeMarket.dao.UserDao;
import com.laioffer.tradeMarket.entity.Post;
import com.laioffer.tradeMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PostService {
    private final PostDao postDao;
    private final UserDao userDao;
    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    public PostService(PostDao postDao, UserDao userDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    public Post getPost(int postID) {
        return postDao.getPostById(postID);
    }

    public void addPost(Post post) {
        postDao.addPost(post);
    }

    public void editPost(int postID, Post newPost) {
        postDao.editPost(postID, newPost);
    }

    public void deletePost(int postID) {
        // 我认为这里还是需要用一下Authentication的方法进行验证，如果没有验证的话，就有可能所有人都可以通过一个组件随意删除网站上的post
        postDao.deletePost(postID);
    }

    public Set<Post> getAllPostsByKeyword(String word) {
        return postDao.getAllPostsByKeyword(word);
    }

    @Value("${application.bucket.flag-camp-bucket")
    private String bucketName;

    public String uploadFile(MultipartFile file){
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        URL url = s3Client.getUrl(bucketName, fileName);
        fileObj.delete();
        return url.toString();
       // return "File uploaded :" + fileName;

    }

    public byte[] downloadFile(String fileName){
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
            try{
                byte[] content = IOUtils.toByteArray(inputStream);
                return content;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
    }
    public String deleteFile(String fileName){
        s3Client.deleteObject(bucketName, fileName);
        return fileName + "remove...";
    }
    private File convertMultiPartFileToFile(MultipartFile file){
        File convertFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertFile)){
            fos.write(file.getBytes());
        }catch(IOException e){
            log.error("Error converting multipleFile to file", e);
        }
        return convertFile;
    }
}
