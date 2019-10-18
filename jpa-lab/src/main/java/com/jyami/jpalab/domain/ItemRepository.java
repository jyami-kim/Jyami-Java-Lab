package com.jyami.jpalab.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ItemRepository<T extends Item> extends JpaRepository<T, Long> {
}
