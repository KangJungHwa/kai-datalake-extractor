package com.kai.smart.platform.service;


import com.kai.smart.platform.util.HiveConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVPrinter;

@Service
@Slf4j
public class ExtractHiveService {

    public String extractHiveTable(String sql) {
        ResultSet rs=null;
        Statement stmt=null;
        Connection con=null;
        try {

            HiveConnectionUtils connectionUtils = new HiveConnectionUtils();
            con = connectionUtils.getConnection();
            stmt = con.createStatement();

            System.out.println("Running: " + sql);
            rs= stmt.executeQuery(sql);
            CSVPrinter csvPrinter = new CSVPrinter(new FileWriter("c://DEV/ABC.CSV"),  CSVFormat.DEFAULT.withHeader(rs));
            csvPrinter.printRecords(rs);
            csvPrinter.close();
        } catch (Exception e) {
            log.error("ERROR : ",e);
        }finally {
            if(rs!=null) {try {rs.close();} catch (Exception e) {log.error("ERROR : ",e);}}
            if(stmt!=null) {try {stmt.close();} catch (Exception e) {log.error("ERROR : ",e);}}
            if(con!=null) {try {con.close();} catch (Exception e) {log.error("ERROR : ",e);}}
        }
        return "OK";
    }

//    public boolean putObject(String bucket, String objectName, String path ) {
//        boolean result = false;
//        try {
//            // Create a minioClient with the MinIO server playground, its access key and secret key.
//            MinioClient minioClient =
//                    MinioClient.builder()
//                            .endpoint("https://play.min.io")
//                            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
//                            .build();
//
//            // Make 'asiatrip' bucket if not exist.
//            boolean found =
//                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
//            if (!found) {
//                // Make a new bucket called 'asiatrip'.
//                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
//            } else {
//                System.out.println("Bucket 'asiatrip' already exists.");
//            }
//            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
//            // 'asiatrip'.
//            minioClient.uploadObject(
//                    UploadObjectArgs.builder()
//                            .bucket("asiatrip")
//                            .object("asiaphotos-2015.zip")
//                            .filename("/home/user/Photos/asiaphotos.zip")
//                            .build());
//            System.out.println(
//                    "'/home/user/Photos/asiaphotos.zip' is successfully uploaded as "
//                            + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'.");
//        } catch (Exception e) {
//            System.out.println("Error occurred: " + e);
//        }
//        return result;
//    }
}
