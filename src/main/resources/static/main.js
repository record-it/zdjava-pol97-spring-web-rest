class ShoppingListController {

    constructor(uri) {
        this.uri = uir;
        this.data = "";
    }

    async getList(id) {
        await fetch(`${this.uri}/${id}`)
            .then(data => {
                this.items = JSON.parse(data);
            });
    }


}