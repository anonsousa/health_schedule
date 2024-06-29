package br.com.healthcare.schedule.infra.notification;

import org.springframework.stereotype.Component;

@Component
public class MailScheduledAppointment {
    public String buildAppointmentNotificationEmail(String userName, String medicoName, String dataConsulta) {
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
                            <h1>Consulta Agendada!</h1>
                            <p>Olá %s,</p>
                            <p>Sua consulta com o Dr(a). %s foi agendada com sucesso.</p>
                            <p><strong>Data e Hora:</strong> %s</p>
                            <p>Por favor, chegue com 10 minutos de antecedência.</p>
                            <footer>
                                <p>Atenciosamente,</p>
                                <p>Equipe HealthCare</p>
                            </footer>
                        </div>
                    </body>
                </html>
                """.formatted(userName, medicoName, dataConsulta);
    }
}
