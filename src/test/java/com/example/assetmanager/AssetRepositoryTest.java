package com.example.assetmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AssetRepositoryTest {

    @Autowired
    private AssetRepository repository;

    @BeforeEach
    void leereDatenbank() {
        repository.deleteAll();
    }

    @Test
    void findetAssetAnhandDesTitelsOhneGrossKleinschreibung() {
        Asset asset = new Asset();
        asset.setTitle("Sonnenuntergang am See");
        asset.setAuthor("Test Autor");
        repository.save(asset);

        List<Asset> ergebnis = repository.findByTitleContainingIgnoreCase("SONNE");

        assertThat(ergebnis).hasSize(1);
        assertThat(ergebnis.get(0).getTitle()).isEqualTo("Sonnenuntergang am See");
    }

    @Test
    void findetNichtsBeiUnbekanntemTitel() {
        Asset asset = new Asset();
        asset.setTitle("Sonnenuntergang am See");
        asset.setAuthor("Test Autor");
        repository.save(asset);

        List<Asset> ergebnis = repository.findByTitleContainingIgnoreCase("XYZ");

        assertThat(ergebnis).isEmpty();
    }
}
