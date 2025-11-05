// Modern Animations for Learning Progress Tracker
// Adds scroll animations, hover effects, and interactive elements

document.addEventListener('DOMContentLoaded', function() {
    // Add fade-in animation to cards on page load
    animateOnLoad();
    
    // Add scroll animations
    setupScrollAnimations();
    
    // Add stagger animation to stat cards
    staggerStatCards();
    
    // Add ripple effect to buttons
    addRippleEffect();
    
    // Add number counter animation
    animateNumbers();
});

// Animate elements on page load
function animateOnLoad() {
    const cards = document.querySelectorAll('.card, .stat-card, .glass-card');
    cards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        
        setTimeout(() => {
            card.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, index * 100);
    });
}

// Setup scroll animations
function setupScrollAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);
    
    // Observe all rows
    const rows = document.querySelectorAll('.row');
    rows.forEach(row => {
        observer.observe(row);
    });
}

// Stagger animation for stat cards
function staggerStatCards() {
    const statCards = document.querySelectorAll('.stat-card');
    statCards.forEach((card, index) => {
        card.style.animationDelay = `${index * 0.1}s`;
    });
}

// Add ripple effect to buttons
function addRippleEffect() {
    const buttons = document.querySelectorAll('.btn-modern, .btn-instructor, .btn-admin, .btn');
    
    buttons.forEach(button => {
        button.addEventListener('click', function(e) {
            const ripple = document.createElement('span');
            const rect = this.getBoundingClientRect();
            const size = Math.max(rect.width, rect.height);
            const x = e.clientX - rect.left - size / 2;
            const y = e.clientY - rect.top - size / 2;
            
            ripple.style.width = ripple.style.height = size + 'px';
            ripple.style.left = x + 'px';
            ripple.style.top = y + 'px';
            ripple.classList.add('ripple-effect');
            
            this.appendChild(ripple);
            
            setTimeout(() => {
                ripple.remove();
            }, 600);
        });
    });
}

// Animate numbers counting up
function animateNumbers() {
    const numberElements = document.querySelectorAll('.stat-card h3');
    
    const animateNumber = (element) => {
        const target = parseInt(element.textContent) || 0;
        if (target === 0 || isNaN(target)) return;
        
        const duration = 2000;
        const step = target / (duration / 16);
        let current = 0;
        
        const timer = setInterval(() => {
            current += step;
            if (current >= target) {
                element.textContent = target;
                clearInterval(timer);
            } else {
                element.textContent = Math.floor(current);
            }
        }, 16);
    };
    
    // Observe stat cards and animate when visible
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const h3 = entry.target.querySelector('h3');
                if (h3) {
                    setTimeout(() => animateNumber(h3), 300);
                }
                observer.unobserve(entry.target);
            }
        });
    }, { threshold: 0.5 });
    
    document.querySelectorAll('.stat-card').forEach(card => {
        observer.observe(card);
    });
}

// Add CSS for ripple effect
const style = document.createElement('style');
style.textContent = `
    .ripple-effect {
        position: absolute;
        border-radius: 50%;
        background: rgba(255, 255, 255, 0.6);
        transform: scale(0);
        animation: ripple 0.6s ease-out;
        pointer-events: none;
    }
    
    @keyframes ripple {
        to {
            transform: scale(2);
            opacity: 0;
        }
    }
    
    button {
        position: relative;
        overflow: hidden;
    }
`;
document.head.appendChild(style);

// Smooth scroll for anchor links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Add parallax effect to welcome card
window.addEventListener('scroll', () => {
    const welcomeCard = document.querySelector('.welcome-card');
    if (welcomeCard) {
        const scrolled = window.pageYOffset;
        welcomeCard.style.transform = `translateY(${scrolled * 0.3}px)`;
    }
});

// Add hover sound effect (optional, can be disabled)
function addHoverSounds() {
    const cards = document.querySelectorAll('.card, .stat-card');
    cards.forEach(card => {
        card.addEventListener('mouseenter', () => {
            // You can add a subtle sound here if desired
            card.style.transition = 'all 0.3s cubic-bezier(0.4, 0, 0.2, 1)';
        });
    });
}

// Initialize
addHoverSounds();

console.log('🎨 Modern animations loaded successfully!');
