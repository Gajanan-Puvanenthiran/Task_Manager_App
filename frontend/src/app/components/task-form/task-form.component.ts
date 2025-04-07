import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { TaskService } from '../../services/task.service';
import { Task } from '../../model/task.model';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, MatCardModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent implements OnInit {
  taskForm: FormGroup;
  taskId: number | null = null;
  statusOptions: string[] = ['TO_DO', 'IN_PROGRESS', 'DONE'];

  constructor(
    private fb: FormBuilder,
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      status: ['TO_DO'],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.taskId = params['id'] ? +params['id'] : null;
      if (this.taskId) {
        const task = this.taskService.getTaskById(this.taskId);
        if (task) {
          this.taskForm.patchValue(task);
        }
      }
    });
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      const task: Task = {
        id: this.taskId,
        ...this.taskForm.value,
      };
      if (this.taskId) {
        this.taskService.updateTask(this.taskId, task).subscribe(() =>{
          this.router.navigate(["/tasks"]);
        });
      } else {
        this.taskService.createTask(task).subscribe(()=>{
          this.router.navigate(["/tasks"]);
        });
      }
      
    }
  }
}