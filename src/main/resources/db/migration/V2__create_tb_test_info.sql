CREATE TABLE IF NOT EXISTS `tb_test_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `TEXT` varchar(255) COLLATE utf8_bin NOT NULL,
  `NUM` int(10),
  `is_deleted` BOOLEAN NOT NULL DEFAULT false,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;