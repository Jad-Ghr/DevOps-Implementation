# Jenkins Kubernetes Setup Guide

## Option 1: Install kubectl in Jenkins Container

If Jenkins is running in Docker, you can install kubectl:

```bash
# In Jenkins container
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl
mv kubectl /usr/local/bin/
```

## Option 2: Use Jenkins with Kubernetes Plugin

For better integration, use the Jenkins Kubernetes plugin:

1. Install "Kubernetes" plugin in Jenkins
2. Configure cloud settings with kubeconfig

## Option 3: Local Minikube Setup

For development:

```bash
# Install Minikube (on your host machine)
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube

# Start Minikube
minikube start

# Copy kubeconfig to Jenkins
mkdir -p ~/.kube
cp ~/.kube/config ~/.kube/jenkins-config
# Mount this file into Jenkins container
```

## Docker Compose with kubectl

Update your docker-compose.yml to include kubectl:

```yaml
jenkins:
  image: jenkins/jenkins:lts
  volumes:
    - ./kubeconfig:/root/.kube/config:ro
    - /usr/local/bin/kubectl:/usr/local/bin/kubectl:ro
```

## Quick Setup for Testing

If you want to test Kubernetes deployment locally:

1. Install Minikube on your host
2. Copy kubeconfig to Jenkins workspace
3. Update Jenkinsfile to use the config file

Would you like me to help you set up any of these options?