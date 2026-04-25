-- Create SonarQube database and user
CREATE DATABASE sonarqube;
CREATE USER sonar WITH PASSWORD 'sonar';
ALTER ROLE sonar SET client_encoding TO 'utf8';
ALTER ROLE sonar SET default_transaction_isolation TO 'read committed';
ALTER ROLE sonar SET default_transaction_deferrable TO on;
ALTER ROLE sonar SET default_transaction_read_only TO off;
GRANT ALL PRIVILEGES ON DATABASE sonarqube TO sonar;
