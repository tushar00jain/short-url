# Short URL

To get started, run
 - ```docker-compose up -d db```
 - ```docker-compose run --service-ports activator``` and inside the container ```./run.sh```

# Routes

  - ```localhost:9000``` Index page with option to shorten url
  - ```localhost:9000/rid/:url``` redirects to original page
  - ```localhost:9000/api/v1/group``` Grouped data by ip address
