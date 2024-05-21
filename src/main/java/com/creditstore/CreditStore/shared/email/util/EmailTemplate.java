package com.creditstore.CreditStore.shared.email.util;

public class EmailTemplate {

  public static String generateCodeOtpBody(String op){
      return "<!DOCTYPE html>" +
          "<html lang=\"es\">" +
          "<head>" +
          "    <meta charset=\"UTF-8\">" +
          "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
          "    <title>Restablecer contraseña</title>" +
          "    <style>" +
          "        body {" +
          "            font-family: Arial, sans-serif;" +
          "            background-color: #f4f4f4;" +
          "            margin: 0;" +
          "            padding: 0;" +
          "        }" +
          "        .container {" +
          "            max-width: 600px;" +
          "            margin: 0 auto;" +
          "            padding: 20px;" +
          "            background-color: #ffffff;" +
          "            border-radius: 8px;" +
          "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);" +
          "        }" +
          "        h1 {" +
          "            color: #333333;" +
          "        }" +
          "        p {" +
          "            color: #666666;" +
          "        }" +
          "        h2 {" +
          "            background-color: #e42322;" +
          "            border-radius: 4px;" +
          "            padding: 16px;" +
          "            width: 200px;" +
          "            margin: auto;" +
          "            text-align: center;" +
          "            color: #fff;" +
          "        }" +
          "        img {" +
          "            display: block;" +
          "            margin: 20px auto;" +
          "            max-width: 100%;" +
          "            height: auto;" +
          "            border-radius: 8px;" +
          "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);" +
          "        }" +
          "        .custom-table {" +
          "            background: #fff url('https://micuenta.upc.edu.pe/img/trama_xs.png');" +
          "            border: 2px solid #eaeaea;" +
          "            font-family: Arial, Helvetica, sans-serif;" +
          "            margin: 0 auto;" +
          "            padding: 0;" +
          "            width: 600px;" +
          "        }" +
          "    </style>" +
          "</head>" +
          "<body>" +
          "    <table class=\"container custom-table\">" +
          "        <tr>" +
          "            <td colspan=\"2\" style=\"background-color:#fff; text-align:right\">" +
          "                <div style=\"padding:20px 36px\">" +
          "                    <img data-imagetype=\"External\" src=\"https://media.discordapp.net/attachments/1157361109930623079/1242224993371426846/Component_13.png?ex=664d0fad&is=664bbe2d&hm=ced3ba688fc83b2d9f8175f42f73e15f577272db1f62a9e06ec4e7d0c297926b&=&format=webp&quality=lossless&width=832&height=671\" style=\"width:150px\">" +
          "                </div>" +
          "            </td>" +
          "            <td></td>" +
          "        </tr>" +
          "        <tr>" +
          "            <td colspan=\"2\" style=\"padding:40px 36px\">" +
          "                <p>Estimado usuario de <strong>CreditStore</strong>,</p>" +
          "                <p>Hemos recibido una solicitud de cambio de contraseña. Utiliza el siguiente código para continuar la operación:</p>" +
          "                <div style=\"background-color:#e42322; border-radius:4px; padding:16px; width:200px; margin:auto; text-align:center; color:#fff\">" +
          "                    <strong style=\"text-decoration:none; color:#fff\">"+op+"</strong>" +
          "                </div>" +
          "                <p>Gracias</p>" +
          "            </td>" +
          "        </tr>" +
          "        <tr>" +
          "            <td bgcolor=\"#ffffff\" style=\"text-align:center; padding:20px 36px; font-size:10px\">" +
          "                <strong>© Copyright 2024 CreditStore, Todos los derechos reservados</strong>" +
          "            </td>" +
          "        </tr>" +
          "    </table>" +
          "</body>" +
          "</html>";
  }

  public static String account_created_body = "<!DOCTYPE html>" +
      "<html lang=\"es\">" +
      "<head>" +
      "    <meta charset=\"UTF-8\">" +
      "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
      "    <title>Bienvenido a ChambeaPe</title>" +
      "    <style>" +
      "        body {" +
      "            font-family: Arial, sans-serif;" +
      "            background-color: #f4f4f4;" +
      "            margin: 0;" +
      "            padding: 0;" +
      "        }" +
      "        .container {" +
      "            max-width: 600px;" +
      "            margin: 0 auto;" +
      "            padding: 20px;" +
      "            background-color: #ffffff;" +
      "            border-radius: 8px;" +
      "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);" +
      "        }" +
      "        h1 {" +
      "            color: #333333;" +
      "        }" +
      "        p {" +
      "            color: #666666;" +
      "        }" +
      "        h2 {" +
      "            background-color: #e42322;" +
      "            border-radius: 4px;" +
      "            padding: 16px;" +
      "            width: 200px;" +
      "            margin: auto;" +
      "            text-align: center;" +
      "            color: #fff;" +
      "        }" +
      "        img {" +
      "            display: block;" +
      "            margin: 20px auto;" +
      "            max-width: 100%;" +
      "            height: auto;" +
      "            border-radius: 8px;" +
      "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);" +
      "        }" +
      "        .custom-table {" +
      "            background: #fff url('https://micuenta.upc.edu.pe/img/trama_xs.png');" +
      "            border: 2px solid #eaeaea;" +
      "            font-family: Arial, Helvetica, sans-serif;" +
      "            margin: 0 auto;" +
      "            padding: 0;" +
      "            width: 600px;" +
      "        }" +
      "    </style>" +
      "</head>" +
      "<body>" +
      "    <table class=\"container custom-table\">" +
      "        <tr>" +
      "            <td colspan=\"2\" style=\"background-color:#fff; text-align:right\">" +
      "                <div style=\"padding:20px 36px\">" +
      "                    <img data-imagetype=\"External\" src=\"https://i.postimg.cc/Jhq3jLJz/167279777.png\" style=\"width:60px\">" +
      "                </div>" +
      "            </td>" +
      "            <td></td>" +
      "        </tr>" +
      "        <tr>" +
      "            <td colspan=\"2\" style=\"padding:40px 36px\">" +
      "                <h1>Bienvenido a ChambeaPe</h1>" +
      "                <p>Estimado usuario de <strong>ChambeaPe</strong>,</p>" +
      "                <p>Se ha creado su cuenta en nuestra plataforma con éxito. ¡Bienvenido!</p>" +
      "                <img src=\"https://media1.tenor.com/m/6xwjsmMIAIoAAAAd/happy-happy-dog.gif\" alt=\"Logo\">" +
      "                <p>Esperamos que disfrutes de nuestros servicios y encuentres lo que buscas.</p>" +
      "                <p>Gracias</p>" +
      "            </td>" +
      "        </tr>" +
      "        <tr>" +
      "            <td bgcolor=\"#ffffff\" style=\"text-align:center; padding:20px 36px; font-size:10px\">" +
      "                <strong>© Copyright 2023 ChambeaPe, Todos los derechos reservados</strong>" +
      "            </td>" +
      "        </tr>" +
      "    </table>" +
      "</body>" +
      "</html>";


  //Información de la cuenta actualizada
  public static String account_updated_body = "<!DOCTYPE html>" +
      "<html lang=\"es\">" +
      "<head>" +
      "    <meta charset=\"UTF-8\">" +
      "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
      "    <title>Información de la Cuenta Actualizada</title>" +
      "    <style>" +
      "        body {" +
      "            font-family: Arial, sans-serif;" +
      "            background-color: #f4f4f4;" +
      "            margin: 0;" +
      "            padding: 0;" +
      "        }" +
      "        .container {" +
      "            max-width: 600px;" +
      "            margin: 0 auto;" +
      "            padding: 20px;" +
      "            background-color: #ffffff;" +
      "            border-radius: 8px;" +
      "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);" +
      "        }" +
      "        h1 {" +
      "            color: #333333;" +
      "        }" +
      "        p {" +
      "            color: #666666;" +
      "        }" +
      "        h2 {" +
      "            background-color: #e42322;" +
      "            border-radius: 4px;" +
      "            padding: 16px;" +
      "            width: 200px;" +
      "            margin: auto;" +
      "            text-align: center;" +
      "            color: #fff;" +
      "        }" +
      "        img {" +
      "            display: block;" +
      "            margin: 20px auto;" +
      "            max-width: 100%;" +
      "            height: auto;" +
      "            border-radius: 8px;" +
      "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);" +
      "        }" +
      "        .custom-table {" +
      "            background: #fff url('https://micuenta.upc.edu.pe/img/trama_xs.png');" +
      "            border: 2px solid #eaeaea;" +
      "            font-family: Arial, Helvetica, sans-serif;" +
      "            margin: 0 auto;" +
      "            padding: 0;" +
      "            width: 600px;" +
      "        }" +
      "    </style>" +
      "</head>" +
      "<body>" +
      "    <table class=\"container custom-table\">" +
      "        <tr>" +
      "            <td colspan=\"2\" style=\"background-color:#fff; text-align:right\">" +
      "                <div style=\"padding:20px 36px\">" +
      "                    <img data-imagetype=\"External\" src=\"https://i.postimg.cc/Jhq3jLJz/167279777.png\" style=\"width:60px\">" +
      "                </div>" +
      "            </td>" +
      "            <td></td>" +
      "        </tr>" +
      "        <tr>" +
      "            <td colspan=\"2\" style=\"padding:40px 36px\">" +
      "                <h1>Información de la Cuenta Actualizada</h1>" +
      "                <p>Estimado usuario de <strong>ChambeaPe</strong>,</p>" +
      "                <p>Se ha actualizado la información de su cuenta en nuestra plataforma con éxito.</p>" +
      "                <img src=\"https://media1.tenor.com/m/z3sxfsXpHtUAAAAd/beaver-carrot.gif\" alt=\"Logo\">" +
      "                <p>En caso de que no haya sido usted, por favor, contacte con nosotros.</p>" +
      "                <p>Gracias por confiar en nosotros</p>" +
      "            </td>" +
      "        </tr>" +
      "        <tr>" +
      "            <td bgcolor=\"#ffffff\" style=\"text-align:center; padding:20px 36px; font-size:10px\">" +
      "                <strong>© Copyright 2023 ChambeaPe, Todos los derechos reservados</strong>" +
      "            </td>" +
      "        </tr>" +
      "    </table>" +
      "</body>" +
      "</html>";

}