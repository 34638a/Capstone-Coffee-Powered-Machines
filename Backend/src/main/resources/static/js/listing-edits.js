

let postEditForm = () =>  {
    let formdata = document.getElementById("edit-form");
    console.log("Sending Form");
    let target = window.location.href + "/update";
    (async () => {
        const rawResponse = await fetch(target, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({a: 1, b: 'Textual content'})
        });
        const content = await rawResponse.json();

        console.log(content);
    })();
}