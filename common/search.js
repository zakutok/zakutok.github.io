var index = lunr(function () {
    this.field('content')
    this.ref('ref')

    data.forEach(function (post) {
        this.add(post)
    }, this)
})