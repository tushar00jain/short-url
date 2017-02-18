# Short URL

Run ```docker-compose up -d``` to get started.
Typesafe activator might take some time to resolve dependiencies for the first time.
Run ```docker logs -f --tail=100 app``` to check the progress.

# Routes

  - ```localhost:9000``` Index page with option to shorten url
  - ```localhost:9000/rid/:url``` redirects to original page
  - ```localhost:9000/api/v1/group``` Grouped data by ip address
