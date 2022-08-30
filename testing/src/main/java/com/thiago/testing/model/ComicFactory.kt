package com.thiago.testing.model

import com.thiago.core.domain.model.Comic

class ComicFactory {

    @Suppress("MagicNumber")
    fun create(comic: FakeComic) = when (comic) {
        FakeComic.FakeComic1 -> Comic(
            2211506,
            "http://fakecomigurl.jpg"
        )
    }

    sealed class FakeComic {
        object FakeComic1 : FakeComic()
    }
}