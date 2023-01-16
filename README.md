To import the job dataset seek_australia.csv:

copy the file in the directory postgres-data

run this command:

docker exec -it 89d847bd71b3 psql -U postgres -d job -c "COPY job FROM '/var/lib/postgresql/data/seek_australia.csv' DELIMITER ';' CSV HEADER;"

where:

- 89d847bd71b3 is the postgres container id

- postgres is the username

- -d job is the database name

- COPY job is the table name

- /var/lib/postgresql/data/ is the path specified in the docker-compose