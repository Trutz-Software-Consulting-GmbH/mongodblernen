git rev-list --children --all | grep $(git rev-parse HEAD) | head -1 | awk '{split($0,children," "); print children[2]}'
