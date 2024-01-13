async function createAccount() {
    let nome = document.getElementById('nome').value;
    let cpf = document.getElementById('cpf').value;
    let email = document.getElementById('email').value;
    let senha = document.getElementById('senha').value;

    console.log(nome, senha);

    const response = await fetch("http://localhost:8080/user", {
      method: "POST",
      headers:{
        "Content-Type": "application/json; charset=utf8",
        Accept: "application/json",
      },
      body: JSON.stringify({
        nome: nome,
        senha: senha,
        cpf: cpf,
        email: email,
      }),
    });
  }