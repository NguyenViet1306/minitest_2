findAllProduct()

function openModal(){
    $("#myModal").modal("show")
}
// Hiện thị tất cả theo ajax
function findAllProduct() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products",
        success: function (data) {
            document.getElementById("display").innerHTML = display(data)
        }
    })
}

function findById(id){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products" +id,
        success: function (data) {
            localStorage.setItem("updateId", id)
            document.getElementById("name").value = data.name
            document.getElementById("price").value = data.price
            document.getElementById("category").value = data.category
            document.getElementById("description").value = data.description
            document.getElementById("image").value = data.image
        }
    })
}

function deleteById(id) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/products" +id,
        success: function () {
            findAllProduct()
        }
    })
}
// lấy giá trị để trả về bẳng table
function display(data) {
    let content = ""
    content += "<table><tr>" +
        "<th>STT</th>" +
        "<th>Name</th>" +
        "<th>Price</th>" +
        "<th>Category</th>" +
        "<th>Description</th>" +
        "<th>Image</th>" +
        "<th>Action</th>" +
        "</tr>"

    for (let i = 0; i < data.length; i++) {
        content += "<tr>" +
            "<td>" + (i+1) + "</td>" +
            "<td>" + data[i].name + "</td>" +
            "<td>" + data[i].price + "</td>" +
            "<td>" + data[i].category.name + "</td>" +
            "<td>" + data[i].description + "</td>" +
            "<td><img width='100px' height='100px' src='../../../static/image/" + data[i].image + "' ></td>" +
            "<td><button onclick='findById(" + data[i].id +")'>Update</button> </td>" +
            "<td><button onclick='deleteById(" + data[i].id +")'>Delete</button></td>" +
            "</tr>"
    }
    content += "</table>"
    return content;
}


// tạo sản phẩm mới

function createProduct() {
    let name = $("#name").val()
    let price = $("#price").val()
    let category = $("#category").val()
    let description = $("#description").val()
    let image = $("#image")

    let product = {
        name: name,
        price: price,
        category: { id : category},
        description: description,
        image: image
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/api/products",
        data: JSON.stringify(product),
        success: function (data) {
            document.getElementById("messageCreate").innerHTML = "Create successfully!";
            findAllProduct()
        }
    })
    event.preventDefault();
}


function showCategory() {
    openModal();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products/category/display",
        success: function (data) {
            let content = ""
            for (let i=0; i< data.length;i++){
                content += `<option value="${data[i].id}">${data[i].name}</option>`
            }
            document.getElementById("image").innerHTML = content;
        }
    })
}

