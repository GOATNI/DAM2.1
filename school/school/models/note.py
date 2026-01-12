from odoo import models, fields, api


class note(models.Model):
    _name = 'school.note'
    _description = 'Describe la clase note'
    
    note = fields.Integer()
    
    subject_id = fields.Many2one('school.subject')
    course_subject_id=fields.Many2one(related="subject_id.course_id")
    
    
    student_id = fields.Many2one('school.student')
    #course_student_id=fields.Many2one(related="student_id.course_id")
    
    _sql_constraints = [
        ('nota_unica_alumno_asig', 'unique(subject_id, student_id)',
            'El alumno solo puede tener una nota por asignatura'),
    ]
    
    
    