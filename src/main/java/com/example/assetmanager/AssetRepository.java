package com.example.assetmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByTitleContainingIgnoreCase(String title);
    List<Asset> findByAuthorContainingIgnoreCase(String author);
}