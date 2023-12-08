package ma.xproce.inventoryservice;

import ma.xproce.inventoryservice.entities.Creator;
import ma.xproce.inventoryservice.entities.Video;
import ma.xproce.inventoryservice.repo.CreatorRepository;
import ma.xproce.inventoryservice.repo.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(CreatorRepository creatorRepository, VideoRepository videoRepository){
        return args -> {
            Creator creator1 = new Creator();
            creator1.setName("Moad Ibourek");
            creator1.setEmail("mo@example.com");

            Creator creator2 = new Creator();
            creator2.setName("Michell Scott");
            creator2.setEmail("bigj@example.com");

            Video video1 = new Video();
            video1.setName("J. Cole 1 Hour Chill Songs");
            video1.setUrl("https://www.youtube.com/watch?v=nfitZKO1jM0");
            video1.setDescription("A beginner's guide to J. Cole");
            video1.setDatePublication(LocalDate.now());
            video1.setCreator(creator1);

            Video video2 = new Video();
            video2.setName("RKHRUANGBIN VIBES VOL. 9");
            video2.setUrl("https://www.youtube.com/watch?v=hPoGB3Zx7-M");
            video2.setDescription("Good Vibes");
            video2.setDatePublication(LocalDate.now());
            video2.setCreator(creator2);

            try {
                creatorRepository.saveAll(List.of(creator1, creator2));
                videoRepository.saveAll(List.of(video1, video2));

                logger.info("Data insertion successful!");

                logger.info("Creators:");
                creatorRepository.findAll().forEach(creator -> logger.info(creator.toString()));

                logger.info("\nVideos:");
                videoRepository.findAll().forEach(video -> logger.info(video.toString()));
            } catch (Exception e) {
                logger.error("Error occurred during data insertion: {}", e.getMessage());
                e.printStackTrace();
            }
        };
    }

}
