DROP TABLE IF EXISTS quote;
CREATE TABLE quote (
  id BIGINT AUTO_INCREMENT,
  isin CHAR(12) NOT NULL,
  bid DECIMAL,
  ask DECIMAL,
  bidSize INTEGER,
  askSize INTEGER
);
ALTER TABLE quote ADD CONSTRAINT check_quote CHECK(length(isin) = 12 OR bid < ask);
ALTER TABLE quote ADD CONSTRAINT pk_quote PRIMARY KEY (id);

DROP TABLE IF EXISTS energy_level;
CREATE TABLE energy_level (
  isin CHAR(12) NOT NULL,
  value DECIMAL NOT NULL
);
ALTER TABLE energy_level ADD CONSTRAINT check_energy_level CHECK(length(isin) = 12);
ALTER TABLE energy_level ADD CONSTRAINT pk_energy_level PRIMARY KEY (isin);

DROP INDEX IF EXISTS ix_energy_level_isin;
CREATE INDEX ix_energy_level_isin ON energy_level (isin);