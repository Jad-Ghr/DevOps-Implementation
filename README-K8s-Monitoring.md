# Spring Boot Microservices CI/CD with Kubernetes and Monitoring

This project includes a complete CI/CD pipeline for Spring Boot microservices with Docker, Kubernetes deployment, and monitoring.

## Prerequisites

- Jenkins with JDK11 installed
- Docker and Docker Compose
- Kubernetes cluster (Minikube, EKS, AKS, etc.) - **Optional**
- kubectl configured to access the cluster - **Optional**
- Docker Hub account (or other registry) - **Optional**

## Jenkins Setup

### Credentials (Optional - for Docker Push)
Add the following credentials in Jenkins for Docker image push:
- `docker-username`: Your Docker registry username
- `docker-password`: Your Docker registry password

### Environment Variables
Update the following in the Jenkinsfile:
- `DOCKER_REGISTRY`: Your Docker registry (e.g., `your-dockerhub-username`) - only needed if pushing images

## Kubernetes Deployment

The `k8s/` directory contains Kubernetes manifests for:
- **Databases**: MySQL, PostgreSQL, MongoDB
- **Microservices**: Eureka, Gateway, Answer, Course, Exam, User services
- **Frontend**: Angular application
- **Monitoring**: Prometheus and Grafana

### Deploying to Kubernetes

1. Ensure kubectl is configured to access your cluster
2. The Jenkins pipeline will automatically deploy all components
3. Services will be exposed via LoadBalancer (adjust as needed for your cluster)

### Accessing Services

After deployment:
- Frontend: `http://<load-balancer-ip>:8090`
- Gateway: `http://<load-balancer-ip>:8888`
- Eureka: `http://<load-balancer-ip>:8761`

## Monitoring

### Prometheus
- URL: `http://<load-balancer-ip>:9090`
- Scrapes metrics from Kubernetes service endpoints

### Grafana
- URL: `http://<load-balancer-ip>:3000`
- Default credentials: `admin` / `admin`
- Add Prometheus as a data source: `http://prometheus:9090`

## Pipeline Stages

1. Build Common Modules
2. Build Individual Services
3. Test Services (conditional)
4. SonarQube Analysis (conditional)
5. Test Frontend (conditional)
6. Build Frontend
7. Docker Cleanup
8. Docker Build
9. Deploy Containers
10. **Docker Image Push**
11. **Deploy to Kubernetes**
12. **Setup Monitoring**

## Notes

- Update image references in Kubernetes YAML files if using a different registry
- Configure persistent volumes according to your cluster's storage classes
- Adjust resource limits and requests based on your requirements
- For production, use secrets for database passwords and other sensitive data

## Troubleshooting

- **Pipeline fails on credentials**: Docker push and K8s stages are optional and will skip if credentials aren't configured
- **kubectl not found**: Install kubectl or skip K8s deployment
- **Image pull errors**: Ensure images are pushed to accessible registry
- **Service not accessible**: Check LoadBalancer external IPs or use port forwarding