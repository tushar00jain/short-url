# Short URL

To get started, run
 - ```docker-compose up -d db```
 - ```docker-compose run --service-ports activator``` and inside the container ```./run.sh```

Typesafe activator might take some time to resolve dependiencies for the first time.
Run ```docker logs -f --tail=100 app``` to track the progress.

# Routes

  - ```localhost:9000``` Index page with option to shorten url
  - ```localhost:9000/rid/:url``` redirects to original page
  - ```localhost:9000/api/v1/group``` Grouped data by ip address
