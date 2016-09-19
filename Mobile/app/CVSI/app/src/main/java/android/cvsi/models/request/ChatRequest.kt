package android.cvsi.models.request


class ChatRequest {
    var name: String? = null

    constructor() {
    }

    constructor(name: String) {
        this.name = name
    }
}
