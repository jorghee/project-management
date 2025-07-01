import { createContext, useState, useCallback, useContext } from 'react';
import { v4 as uuidv4 } from 'uuid';

const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [notifications, setNotifications] = useState([]);

  const addNotification = useCallback((message, type = 'info') => {
    const id = uuidv4();
    setNotifications((prev) => [...prev, { id, message, type }]);
    setTimeout(() => {
      setNotifications((current) => current.filter((n) => n.id !== id));
    }, 5000); // El toast desaparece después de 5 segundos
  }, []);

  const removeNotification = useCallback((id) => {
    setNotifications((current) => current.filter((n) => n.id !== id));
  }, []);

  return (
    <NotificationContext.Provider value={{ addNotification }}>
      {children}
      <ToastContainer notifications={notifications} removeNotification={removeNotification} />
    </NotificationContext.Provider>
  );
};

export const useNotifier = () => useContext(NotificationContext);

// Componente visual para los toasts
const ToastContainer = ({ notifications, removeNotification }) => {
  const typeClasses = {
    success: 'bg-green-500',
    error: 'bg-red-500',
    warning: 'bg-yellow-500',
    info: 'bg-blue-500',
  };

  return (
    <div className="fixed top-5 right-5 z-50 space-y-2">
      {notifications.map((n) => (
        <div
          key={n.id}
          className={`flex items-center justify-between p-4 rounded-md shadow-lg text-white ${typeClasses[n.type]}`}
        >
          <span className="font-medium">{n.message}</span>
          <button onClick={() => removeNotification(n.id)} className="ml-4 text-xl font-bold">×</button>
        </div>
      ))}
    </div>
  );
};
