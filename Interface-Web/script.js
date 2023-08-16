document.getElementById("executeButton").addEventListener("click", async () => {
    try {
      const response = await fetch("http://ec2-52-67-191-251.sa-east-1.compute.amazonaws.com:8080/texxsupply/api/integration/ordersToDimona", {
        method: "POST",
        mode: "no-cors"
      });
      
      if (response.ok) {
        alert("Integração de pedidos bem-sucedida!");
      } else {
        alert("Não foi possível fazer a integração de nenhum pedido!");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("Ocorreu um erro na requisição.");
    }
  });
  