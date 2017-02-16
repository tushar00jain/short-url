# Run the following commands to get started

```
docker-compose up -d postgres
docker-compose run --service-ports activator 

cd short-url
activator run
```

# DEBUG

  - postgres
  ```
  docker exec -it db bash
  psql -U test
  ```
