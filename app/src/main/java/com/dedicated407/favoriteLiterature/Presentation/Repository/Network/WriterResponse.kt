package com.dedicated407.favoriteLiterature.Presentation.Repository.Network


public class WriterResponse() {
    var docs: List<WriterDTO>? = null

    inner class WriterDTO(
        var author_name: List<String>? = null
    )
}