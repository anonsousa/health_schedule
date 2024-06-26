package br.com.healthcare.schedule.domain.service;

import org.springframework.stereotype.Component;

@Component
public class MailWelcomeTemplate {
    public String buildWelcomeEmail(String userName) {
        return """
                <html>
                    <head>
                        <style>
                            body { font-family: Arial, sans-serif; }
                            .card {
                                background-color: #f9f9f9;
                                border: 1px solid #e1e1e1;
                                border-radius: 8px;
                                padding: 20px;
                                text-align: center;
                                max-width: 500px;
                                margin: auto;
                            }
                            h1 { color: #4CAF50; }
                            p { color: #666; }
                            footer { color: #888; font-size: 12px; }
                        </style>
                    </head>
                    <body>
                        <div class="card">
                            <h1>Bem-vindo à HealthCare, %s!</h1>
                            <p>Olá %s! Estamos muito felizes por ter você conosco. 😊</p>
                            <p>Explore nossos serviços e dê passos em direção a uma saúde melhor.</p>
                            <footer>
                                <p>Atenciosamente,</p>
                                <p>Equipe de Suporte da HealthCare</p>
                            </footer>
                        </div>
                    </body>
                </html>
                """.formatted(userName, userName);
    }
}

