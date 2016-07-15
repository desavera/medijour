docker ps -a | grep airtsbooking | awk '{print $1}' | xargs -i docker rm {}
