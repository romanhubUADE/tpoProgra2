package emailModule;

import priorityQueueModule.SimpleLinkedPriorityQueue;
import priorityQueueModule.SimplePriorityQueue;

public class EmailSystem {

    private final SimplePriorityQueue<Email> inbox;
    private final SimplePriorityQueue<Email> principal;
    private final SimplePriorityQueue<Email> notificaciones;
    private final SimplePriorityQueue<Email> spam;

    public EmailSystem() {
        inbox = new SimpleLinkedPriorityQueue<>();
        principal = new SimpleLinkedPriorityQueue<>();
        notificaciones = new SimpleLinkedPriorityQueue<>();
        spam = new SimpleLinkedPriorityQueue<>();
    }

    public void receiveEmail(Email email) {
        inbox.enqueue(email, email.getPriority().getNumericValue());
    }

    public void classifyTopInboxEmail(Tab tab) {
        Email email = inbox.dequeue();
        email.assignTab(tab);
        getQueueForTab(tab).enqueue(email, email.getPriority().getNumericValue());
    }

    public Email readTopEmail(Tab tab) {
        return getQueueForTab(tab).dequeue();
    }

    public Email[] listInbox() { return listQueue(inbox); }

    public Email[] listTab(Tab tab) { return listQueue(getQueueForTab(tab)); }

    public boolean isInboxEmpty() { return inbox.isEmpty(); }
    public boolean isTabEmpty(Tab tab) { return getQueueForTab(tab).isEmpty(); }
    public int inboxSize() { return inbox.size(); }
    public int tabSize(Tab tab) { return getQueueForTab(tab).size(); }

    private SimplePriorityQueue<Email> getQueueForTab(Tab tab) {
        switch (tab) {
            case PRINCIPAL:      return principal;
            case NOTIFICACIONES: return notificaciones;
            case SPAM:           return spam;
            default: throw new IllegalArgumentException("Pestana desconocida: " + tab);
        }
    }

    private Email[] listQueue(SimplePriorityQueue<Email> queue) {
        int n = queue.size();
        Email[] result = new Email[n];
        SimplePriorityQueue<Email> temp = new SimpleLinkedPriorityQueue<>();

        for (int i = 0; i < n; i++) {
            Email email = queue.dequeue();
            result[i] = email;
            temp.enqueue(email, email.getPriority().getNumericValue());
        }

        while (!temp.isEmpty()) {
            Email email = temp.dequeue();
            queue.enqueue(email, email.getPriority().getNumericValue());
        }

        return result;
    }
}
