document.getElementById("ButtonDimona").addEventListener("click", async () => {
    try {
      const response = await fetch("url-do-seu-end-point", {
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
  