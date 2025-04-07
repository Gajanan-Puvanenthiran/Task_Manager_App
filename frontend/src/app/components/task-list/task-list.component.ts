import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { TaskService } from '../../services/task.service';
import { Task } from '../../model/task.model';
import { TaskDetailsComponent } from '../task-details/task-details.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, RouterModule, FormsModule, MatSelectModule, MatDialogModule, MatCardModule, MatIconModule, MatSnackBarModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  displayedColumns: string[] = ['id', 'title', 'description', 'status', 'actions'];
  filterStatus: string = '';
  statusOptions: string[] = ['TO_DO', 'IN_PROGRESS', 'DONE', ''];

  constructor(private taskService: TaskService, private router: Router, public dialog: MatDialog, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.taskService.getAllTasks().subscribe({
      next: (tasks) => {
        this.tasks = tasks;
      },
      error: (error) => {
        console.error('Get tasks error:', error);
        this.snackBar.open('Failed to load tasks. please Login Again', 'Close', { duration: 3000, panelClass: ['error-snackbar'] });
      }
    });
  }

  filterTasks(): Task[] {
    if (!this.filterStatus) {
      return this.tasks;
    }
    return this.tasks.filter((task) => task.status === this.filterStatus);
  }

  editTask(id: number): void {
    this.router.navigate(['/tasks/edit', id]);
  }

  deleteTask(id: number): void {
    if (confirm('Are you sure you want to delete this task?')) {
      this.taskService.deleteTask(id).subscribe({
        next: () => {
          this.snackBar.open('Task deleted successfully!', 'Close', { duration: 3000 });
          this.ngOnInit(); // Refresh tasks
        },
        error: (error) => {
          console.error('Delete task error:', error);
          this.snackBar.open('Failed to delete task.', 'Close', { duration: 3000, panelClass: ['error-snackbar'] });
        }
      });
    }
  }

  showDetails(task: Task): void {
    this.dialog.open(TaskDetailsComponent, {
      width: '400px',
      data: { task: task },
    });
  }
}