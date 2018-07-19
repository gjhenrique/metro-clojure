## Motivation
A few years ago, I saw a repo called [MetroGit](https://github.com/vbarbaresi/MetroGit).
The idea is to build a git graph based on the metro map.

Basically, each commit is a station and a connection creates a merge.

I was trying to learn Clojure and I thought: "Why don't I build something based on that idea?!?!"
So, that's when metro-clojure was born.

Takes a metro map as a json input, builds a DAG (we need to remove the cycles) and traverse the graph generating the correct git commands.

## Generate git commands
```
# São Paulo
# Takes the map ffrom res/sp.json and generates sp.sh
boot generate-git-commands -c sp -f sp.sh

# Takes the map from res/nyc.json and generates nyc.sh
boot generate-git-commands -c nyc -f nyc.sh
```

## Viewing the metro graph
```
# Open graphviz graph
boot view-graph -c nyc
```

## Generate the clojurescript files

```
# Visit http://localhost:3000 to check an animation of the traversal
boot dev

# Optimized js file with the animations
boot prod
```
