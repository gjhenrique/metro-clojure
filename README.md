[![CircleCI](https://circleci.com/gh/gjhenrique/metro-clojure.svg?style=svg)](https://circleci.com/gh/gjhenrique/metro-clojure)

## Motivation
A few years ago, I saw a repo called [MetroGit](https://github.com/vbarbaresi/MetroGit).
This repo is, in fact, a git graph taken from Paris metro map.
Basically, each commit is a station and when two or more connections meet, it creates a merge.

I was trying to learn Clojure and I thought: "Why don't I build something based on that idea?!?!"
So, that's when metro-clojure was born.

This repo takes a *generic* metro map as a json input, builds a DAG and traverse that graph generating the correct git commands.

## Generate git commands
```
# São Paulo
# Takes the map ffrom res/sp.json and generates sp.sh
boot generate-git-commands -c sp -f sp.sh

# New York City
# Takes the map from res/nyc.json and generates nyc.sh
boot generate-git-commands -c nyc -f nyc.sh

# Tokyo
# Takes the map from res/tokyo.json and generates tokyo.sh
boot generate-git-commands -c tokyo -f tokyo.sh
```

## Viewing the metro graph
```
# Open graphviz graph
boot view-graph -c sp
```

## Generate the clojurescript files

```
# Visit http://localhost:3000 to check an animation of the traversal
boot dev

# Optimized js file with the animations
boot prod
```
