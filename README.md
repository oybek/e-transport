
# CAT-PC

[![Build Status](https://travis-ci.org/oybek/catpc.svg?branch=master)](https://travis-ci.org/oybek/catpc)

Cat-pc is a simple chat bot which aggregates and searches sell offers in social network vk.

![](https://thumbs.gfycat.com/FluffyPiercingEkaltadeta-size_restricted.gif)

Implementations:

* Configuration of cat-pc for aggregating and searching offers about computers: [vk.com/catpc](https://vk.com/catpc)
* _You can clone repo and configure project to aggregate and search whatever you want_

# Git workflow

If you want to add feature to project:

1. `git pull master`
2. `git checkout -b your-feature-name`
3. Do your magic
4. `git push origin your-feature-name`
5. Create pull request to `stage`

After `stage` version of cat-pc worked good for 1 day `your-feature-name` will be automatically
merged to `master` branch which is [vk.com/catpc](https://vk.com/catpc)

# Stack

`cats`, `cats-effect`, `monix`, `jenkins`, `intellij-idea`, `postgres`, `doobie`, `flyway`, `http4s`
