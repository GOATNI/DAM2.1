from datetime import timedelta, date # Importamos 'date' además de 'timedelta'
from odoo.exceptions import ValidationError
from odoo import models, fields, api


class admision(models.Model):
    _name = 'hospital.admision'
    _description = 'Esta es la descripcion del ingreso'

    patient_id=fields.Many2one('hospital.patient',string="Paciente", required=True)
    doctor_id=fields.Many2one('hospital.doctor', string="Médico", required=True)
    
    bedroom_number=fields.Char(string="Habitación")
    
    bed_number=fields.Char(string="Cama")
    
    admision_date=fields.Date(string="Fecha de ingreso", default=fields.Date.today) 
    
    symptoms= fields.Char(string="Sintomas")
    
    discharge_date=fields.Date(string="Fecha de alta")

    @api.constrains('admision_date')
    def _check_admision_date(self):
        for record in self:
            # Solo ejecutamos la lógica si hay una fecha de admisión seleccionada
            if record.admision_date:
                # Odoo garantiza que 'record.admision_date' es un objeto 'datetime.date' de Python si no es None.
                
                # 1. Obtenemos la fecha de hoy
                today_date = date.today()
                
                # 2. Calculamos la fecha de hace exactamente 7 días
                one_week_ago = today_date - timedelta(days=7)
                
                # 3. Comparamos
                if record.admision_date < one_week_ago:
                    raise ValidationError("La fecha de ingreso no puede ser hace mas de una semana")
                
                
    # --- NUEVA VALIDACIÓN AQUI ---
    @api.constrains('discharge_date')
    def _check_discharge_date(self):
        for record in self:
            # Solo validamos si el campo de alta no está vacío
            if record.discharge_date:
                today_date = date.today()
                
                # Comprobamos si la fecha de alta es posterior a hoy
                if record.discharge_date > today_date:
                    raise ValidationError("La fecha de alta no puede ser en el futuro.")
                
                # Opcional: También podrías querer asegurarte de que la fecha de alta 
                # sea posterior o igual a la fecha de ingreso, si ambas existen:
                if record.admision_date and record.discharge_date < record.admision_date:
                    raise ValidationError("La fecha de alta debe ser posterior a la fecha de ingreso.")

