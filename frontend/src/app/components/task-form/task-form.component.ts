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
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, MatCardModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, MatSnackBarModule],
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
    private router: Router,
    private snackBar: MatSnackBar,
    private titleService: Title
  ) {
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      status: ['TO_DO'],
    });
  }

  ngOnInit(): void {
    this.titleService.setTitle('Task Form | Task Manager')
    this.route.params.subscribe((params) => {
      this.taskId = params['id'] ? +params['id'] : null;
      if (this.taskId) {
        this.taskService.getTaskById(this.taskId).subscribe(task => {
          if (task) {
            this.taskForm.patchValue(task);
          }
        });
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
        this.taskService.updateTask(this.taskId, task).subscribe({
          next: () => {
            this.snackBar.open('Task updated successfully!', 'Close', { duration: 3000 });
            this.router.navigate(['/tasks']);
          },
          error: (error) => {
            console.error('Update task error:', error);
            this.snackBar.open('Failed to update task.', 'Close', { duration: 3000, panelClass: ['error-snackbar'] });
          }
        });
      } else {
        this.taskService.createTask(task).subscribe({
          next: () => {
            this.snackBar.open('Task created successfully!', 'Close', { duration: 3000 });
            this.router.navigate(['/tasks']);
          },
          error: (error) => {
            console.error('Create task error:', error);
            this.snackBar.open('Failed to create task.', 'Close', { duration: 3000, panelClass: ['error-snackbar'] });
          }
        });
      }
    }
  }

  goBackToTaskList(): void {
    this.router.navigate(['/tasks']);
  }
}