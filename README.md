# Run the following commands to get started

```
docker-compose up -d postgres
docker-compose run --service-ports activator 

cd short-url
activator run
```

```
docker start shorturl_activator_run_2
docker exec -it shorturl_activator_run_2 bash 
docker stop shorturl_activator_run_2
```

# DEBUG

  - postgres
  ```
  docker exec -it db bash
  psql -U test
  ```
