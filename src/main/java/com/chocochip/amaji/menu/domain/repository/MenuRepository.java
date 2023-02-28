package com.chocochip.amaji.menu.domain.repository;

import com.chocochip.amaji.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {
}
