package com.flux.streaming.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final MovieRepository movieRepository;

    public DataInitializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) {
        if (movieRepository.count() > 0) {
            return;
        }

        Instant now = Instant.now();
        String thumbnailUrl = "https://via.placeholder.com/300x450";

        List<Movie> seed = List.of(
                movie("Inception", "A mind-bending thriller", thumbnailUrl, 8880, now),
                movie("Interstellar", "Exploration beyond Earth", thumbnailUrl, 10140, now),
                movie("The Dark Knight", "Batman faces the Joker", thumbnailUrl, 9120, now)
        );

        movieRepository.saveAll(seed);
        log.info("Seeded initial movie data");
    }

    private static Movie movie(String title, String description, String thumbnailUrl, int durationSeconds, Instant createdAt) {
        Movie movie = new Movie();
        movie.setId(UUID.randomUUID());
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setThumbnailUrl(thumbnailUrl);
        movie.setDurationSeconds(durationSeconds);
        movie.setCreatedAt(createdAt);
        return movie;
    }
}
